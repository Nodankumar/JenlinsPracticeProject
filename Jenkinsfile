/*  Get common lib functions from other repository
    for example: at remote url i have file with auditTools.groovy(That has call() with few commands */
library identifier: 'JenkinsSharedLibs@master',
        retriever: modernSCM([$class:'GITSCMSource', remote: 'https://github.com/Nodankumar/JenkinsSharedLibs.git'])

pipeline {
    agent any

    environment {
        MVN_HOME = tool 'Maven_3.9.11'
    }

    stages {

        stage("verify all tools availability"){
            steps{
                auditTools()
            }
        }
        stage('Checkout') {
            steps {
                git 'https://github.com/Nodankumar/JenlinsPracticeProject.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    withEnv(["MVN_HOME=${env.MVN_HOME}"]) {
                        if (isUnix()) {
                            sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean install'
                        } else {
                            bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean install/)
                        }
                    }
                }
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'c09fb0b7-78af-47ac-87b6-d16a51ffd442', // 👈 Your Jenkins credential ID
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    script {
                        if (isUnix()) {
                            sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
                        } else {
                            bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def image = docker.build("nodankumar/seleniumjenkins:${env.BUILD_NUMBER}", ".")
                    image.push()
                }
            }
        }

        stage('Results') {
            steps {
                junit '**/target/reports/*.xml'
                archiveArtifacts 'target/*.jar'
            }
        }
    }
}
