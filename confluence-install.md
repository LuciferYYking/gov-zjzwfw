# Installing Confluence

# 系统要求
- CPU: 2C+
- RAM: 4G+
- HD: 50G+


# 下载 Confluence Server
https://www.atlassian.com/software/confluence/download


# 安装MySQL

```
groupadd mysql
useradd -g mysql mysql
passwd mysql


chown -R mysql:mysql mysql-8.0.20

bin/mysqld --initialize --user=mysql

cp support-files/mysql.server /etc/init.d/mysql
```
5daeSNk,renr

缺少 libprotobuf-lite.so.3.6.1 库：
yum install emacs-filesystem
rpm -ivh protobuf-3.6.1-4.el7.x86_64.rpm