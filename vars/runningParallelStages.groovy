#!/usr/bin/env groovy

def call(Map config = [:]) {
    def targetIpList = config.targetIpList
    config.targetIpList = null
    echo "targetIpList :: ${targetIpList}"

    def stepsForParallel = [:]
    int listSize = targetIpList.tokenize('\n').size
    int seq = 1
    targetIpList.tokenize('\n').each { instance ->
        Map localConfig = new LinkedHashMap(config);
        stepsForParallel[instance] = { ->
            stage("[$seq++}/$listSize}] :: " + instance) {
                def callback = localconfig.callback
                localConfig.ip = instance
                callback(localConfig)
            }
        }
    }
    parallel stepsForParallel

    sleep 3
}