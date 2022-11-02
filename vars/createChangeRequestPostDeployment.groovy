// Author:- alagusundaram.nithyanandam@sap.com

import groovy.json.JsonSlurperClassic

void call(body) {

 String strDataCenter = ''
 String strEnvironmentName = ''
 String strServiceName = ''
 String strReleaseTagName = ''   
}

pipeline {
    agent none
    stages {
        stage('Set Parameters') {
            steps {
                script {
                    strDataCenter= "$DATACENTER"
                    strEnvironmentName ="$ENVIRONMENT"
                    strServiceName= "$SVCNAME"
                    strReleaseTagName ="$IMAGE_VERSION"
                }
            }
        }
        stage('Validate Parameters') {
            steps {
                script {
                    if (!strDataCenter){
                        error('Datacenter must be set . !!!')
                    }
                    if (!strEnvironmentName) {
                        error('EnvirnmentName must be set . !!!')
                    }
                    if (!strServiceName) {
                        error('Service name must be set . !!!')
                    }
                    if (!strReleaseTagName) {
                        error('Release Tag must be set . !!!')
                    }
                }
            }
        }
        stage('Create Standard Change Request') {
            steps {
                script {

                }
            }
        }
    }
}
