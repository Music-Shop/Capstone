pipeline {
    agent any

    stages {
        stage('Build') {
            steps withMaven(maven : 'maven3.6.3') {
				echo 'Building..'
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps withMaven(maven : 'maven3.6.3') {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps withMaven(maven : 'maven3.6.3') {
                echo 'Deploying....'

            }
        }
    }
}