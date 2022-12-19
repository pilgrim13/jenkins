#!/usr/bin/env groovy

/**
 * Clean Workspace
 *
 * @param config
 * @return
 */

def call(Map config = [:]) {
    return script {
        if (config.isActivce == "true") {
            cleanWs(
                    cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuid: true,
                    patterns: [[pattern: "jenkins/**", type: "EXCLUDE"]]
            )
        }
    }
}