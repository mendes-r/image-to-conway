pipeline {

    agent any

    stages {
        stage('Build'){
            steps {
                echo '||||||||||| Building ...'
                sh './gradlew clean build -x test'
            }
        }

        stage('Test'){
            steps {
                echo '||||||||||| Testing ...'
                sh './gradlew test'
            }
        }

        stage('Sonarqube'){
            environment {
                SONAR_CREDENTIALS = credentials('sonar-credentials')
            }
            steps {
                echo '||||||||||| Sonar analysis ...'
                sh './gradlew sonar -Dsonar.host.url=http://sonar:9000 -Dsonar.login=$SONAR_CREDENTIALS_USR -Dsonar.password=$SONAR_CREDENTIALS_PSW'
            }
        }

        stage('Build Image'){
            when {
                branch 'master'
            }
            steps {
                echo '||||||||||| Building Docker image...'
                sh './gradlew docker'
            }
        }

        stage('Push Docker Image'){
            when {
                branch 'master'
            }
            steps {
                echo '||||||||||| Pushing Docker image ...'
            }
        }
    }

    post {
        always {
            junit 'build/test-results/**/*.xml'
        }
        always {
            cleanWs()
        }
    }
}