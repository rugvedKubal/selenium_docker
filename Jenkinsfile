pipeline {
    agent any
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
        stage('Start selenium grid using docker-compose file in project') {
            steps {
                sh 'docker-compose up -d hub chrome firefox'
            }
        }
        stage('Run automation execution using docker-compose file in project') {
            steps {
                sh 'docker-compose up testInChrome testInFirefox'
            }
        }
        stage('Stop selenium grid') {
            steps {
                sh 'docker-compose down'
            }
        }
    }
}