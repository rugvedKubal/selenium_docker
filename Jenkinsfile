pipeline {
    agent none
    stages {
        stage('Build automation execution Jar using maven docker container') {
            agent {
                docker {
                    image 'maven'
                    args '-v /usr/jenkinsSlaveMaven/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build docker image using Dockerfile in automation project') {
            steps {
                script {
                    app = docker.build("rugvedk/selenium-docker")
                }
            }
        }
        stage('Push docker image to docker hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        app.push("${BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }
    }
}