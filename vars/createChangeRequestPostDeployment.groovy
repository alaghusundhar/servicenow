// Author:- alagusundaram.nithyanandam@sap.com

import groovy.json.JsonSlurperClassic

void call(body) {

 String strDataCenter = ''
 String strEnvironmentName = ''
 String strServiceName = ''
 String strReleaseTagName = ''
 String strOathUrl =''   

 pipeline {
    agent any
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
        stage('Servicenow Oauth Token Generation') {
            environment {
                TOKEN_CREDS=credentials('servicenow-token-credential-id-prod')
            }
            steps {
                script {
                    def oAuthToken = sh (script: '''curl --request POST --url $SERVICENOW_PROD_OAUTH 
                    --header 'content-type: application/x-www-form-urlencoded' 
                    --data grant_type=client_credentials 
                    --data client_id=$TOKEN_CREDS_USR 
                    --data client_secret=$TOKEN_CREDS_PSW''', returnStdout: true)
                    println (oAuthToken)     
                }
            }
        }
        stage('Create Standard Change Request') {
            steps {
                script {
                    echo ("Hello World")
                }
            }
        }
    }
}
}
