sshpass -p "rasoul.am1377@gmail.com" scp -o StrictHostKeyChecking=no server.jar 94101618@198.144.184.34:/home/94101618/
sshpass -p "rasoul.am1376@gmail.com" ssh 94101618@198.144.184.34 pkill java
sshpass -p "rasoul.am1376@gmail.com" ssh 94101618@198.144.184.34 nohup java -jar /home/94101618/server.jar &

