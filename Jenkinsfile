pipeline{
   agent any
   
   triggers{
        pollSCM('* * * * *')
    }
   
   tools{
       maven 'maven3'
       jdk 'jdk11'
   }
  stages {
    
    stage('clean'){
          steps{
            script{
last_started=env.STAGE_NAME
}
          sh 'mvn clean'
          }
}    
    stage('Build'){
          steps{
            script{
last_started=env.STAGE_NAME
}
          sh 'mvn compile'
          }
}    
    stage('test'){
          steps{
            script{
last_started=env.STAGE_NAME
}
          sh 'mvn test'
          }
}    
    
          stage("SonarQube analysis") {
            agent any
            steps {
            script{
            last_started=env.STAGE_NAME
            }
              withSonarQubeEnv('sonar-for-spring') {
                sh 'java -version'
                sh 'mvn verify sonar:sonar'
         
              }
            }
          }
          
    stage("Quality Gate Check") {
            steps {
            script{
            last_started=env.STAGE_NAME
            }
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
                
              }
            }
          }
    
    stage("Uploading to artifactory"){
    steps{
    script{
            last_started=env.STAGE_NAME
            }
    rtUpload (
    serverId: 'ARTIFACTORY-SERVER',
    spec: '''{
          "files": [
            {
              "pattern": "target/*.jar",
              "target": "art-doc-dev-loc"
            }
         ]
    }''',
)
}}

stage("Downloading from artifactory"){
steps{
script{
last_started=env.STAGE_NAME
}
rtDownload (
    serverId: 'ARTIFACTORY-SERVER',
    spec: '''{
          "files": [
            {
              "pattern": "art-doc-dev-loc/",
              "target": "war_package_download/"
            }
          ]
    }''',
)
}
}


stage("Uploading to aws server"){
steps{
script{
last_started=env.STAGE_NAME
}
sshagent(['916e53a6-1627-4140-ab0d-7762195972ec']){
                    sh 'scp -r /var/jenkins_home/workspace/spring-pipeline/war_package_download/*.jar ubuntu@13.232.126.40:/home/ubuntu/artifacts'
        }
}
}

}
post{
success {  
             mail bcc: '', body: "<b>Failure</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br>Stage Name: $last_started <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "thesinghanias@gmail.com";  
         }  
 
  
}
}
