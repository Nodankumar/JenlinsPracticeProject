import jenkins.model.*
import hudson.model.UpdateCenter
import hudson.PluginManager

def plugins = [
        "git",
        "workflow-aggregator",  // Pipeline
        "blueocean",
        "docker-plugin",
        "pipeline-github-lib",
        "ssh-agent",
        "credentials-binding",
        "maven-plugin",
        "email-ext"
]

// Timeout and retry config
def instance = Jenkins.getInstance()
def pm = instance.pluginManager
def uc = instance.updateCenter

println("Starting plugin installation...")

plugins.each { pluginShortName ->
    if (!pm.getPlugin(pluginShortName)) {
        println("Installing plugin: ${pluginShortName}")
        def plugin = uc.getPlugin(pluginShortName)
        if (plugin) {
            def installFuture = plugin.deploy()
            installFuture.get() // wait for plugin to be installed
            println("Installed: ${pluginShortName}")
        } else {
            println("Plugin not found in Update Center: ${pluginShortName}")
        }
    } else {
        println("Plugin already installed: ${pluginShortName}")
    }
}

println("All requested plugins processed. Restart Jenkins to complete installation if needed.")

/*
🖥️ If you're running Jenkins on a physical or VM server (non-Docker)
Find where Jenkins stores its data (this is $JENKINS_HOME). Common locations:
/var/lib/jenkins/ (on Linux)
C:\ProgramData\Jenkins (on Windows)
Then create the folder (if not already there):
mkdir -p /var/lib/jenkins/init.groovy.d
And place your install-plugins.groovy file there.

OR

You can just go to manage jenkins, and run this script in script console
*/
