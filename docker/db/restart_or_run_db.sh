#!/bin/bash
docker stop blogDB || true && docker rm blogDB || true

docker run --name blogDB -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=iamSAUser123' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-CU13-ubuntu

sudo docker exec -it blogDB /opt/mssql-tools/bin/sqlcmd \
   -S localhost -U SA -P 'iamSAUser123' \
   -Q "
create database blog
go


use blog
go
create schema dbo
go
CREATE LOGIN blog_user WITH PASSWORD = 'iamAdminUser123'
go
create user blog_user for login blog_user  WITH DEFAULT_SCHEMA = dbo

go

GRANT CONTROL ON DATABASE::blog TO blog_user;
GO

"

sudo docker exec -it blogDB /opt/mssql-tools/bin/sqlcmd \
   -S localhost -U blog_user -P "iamAdminUser123" \
   -Q '
use blog

go
CREATE TABLE [dbo].[notes]
(
    [note_id]  INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    [image]      IMAGE,
    [db_create_time] DATETIME2(6),
    [db_modify_time] DATETIME2(6)
)
go
'
