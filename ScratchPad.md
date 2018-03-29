stop all java process:
 wmic process where "name like '%java%'" delete
 
load base folder & config
if path given switch to using path and override any config found there
 

https://github.com/haywire/haywire
https://github.com/google/leveldb
https://github.com/jwerle/libuv-http
{
 dbEngine: SQLite
 httpServer: libuv
 BsonLib: Bson format (bson cxx)
 Encryption: libssl-crypto
 zlib: compression
 Logger:
}


https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication
https://gist.github.com/fxsjy/5465353

https://python-jose.readthedocs.io/en/latest/

auth:
POST /_session HTTP/1.1
Accept: application/json
Content-Length: 37
Content-Type: application/json
Host: localhost:5984
{
    "name": "root",
    "password": "relax"
}


GET / HTTP/1.1
Accept: application/json
Authorization: Basic base64(username:password)
Host: localhost:5984

/_config/
/_config/_get
/_config/_put,_post

/_session/_post

/_users
/_users/_get
/_users/_put,_post
/_users/_delete


/_database/_get
/_database/_put,_post
/_database/_delete

/{database_name}/_all
/{database_name}/_query
/{database_name}/_get
/{database_name}/_put,_post
/{database_name}/_delete


config()
auth(basic,jwt)
data(saving/retrieving)
serve(simpleHttp, uWSGI)
log()
replication?

SQLITE_TABLES {
 _users{uname, password, name, admin=1/0 roles={database: {read: true, write: true, delete:false},... } }
 _configs{key,value }
 { database1 }
 { database } -> {ID, _id, _doc}
}


Go lucy for search:
 https://github.com/philipsoutham/golucy
 https://lucy.apache.org 
 
 https://www.ardanlabs.com/blog/2013/08/using-c-dynamic-libraries-in-go-programs.html
 http://akrennmair.github.io/golang-cgo-slide
 https://jamesadam.me/2014/11/23/using-c-libraries-with-go/
 
Interface-> then rewirte in go

Might be switching to C++
  https://github.com/facebook/proxygen
  https://code.facebook.com
  
  http router
  
  https://github.com/ziroby/jetty-gradle-hello-world
https://examples.javacodegeeks.com/enterprise-java/jetty/embedded-jetty-server-example/
https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html
https://www.youtube.com/watch?v=rBcwbsEFcVI&t=59s

http://www.oracle.com/technetwork/articles/java/json-1973242.html