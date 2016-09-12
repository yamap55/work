@GrabConfig(systemClassLoader=true)
@Grab('org.eclipse.jetty.aggregate:jetty-all:9.2.6.v20141205')
@Grab('com.sun.jersey:jersey-server:1.18.3')
@Grab('com.sun.jersey:jersey-servlet:1.18.3')
@Grab('com.sun.jersey:jersey-core:1.18.3')
@Grab('com.sun.jersey:jersey-json:1.18.3')
@Grab(group='org.jumpmind.symmetric.jdbc', module='postgresql', version='9.2-1002-jdbc4')

import com.sun.jersey.spi.container.servlet.ServletContainer

import org.eclipse.jetty.server.handler.ContextHandler
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.server.Server


/** */
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import groovy.sql.Sql


@Path("/")
class WebApiResource {

  @GET
  @Path("/test")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ResultDto> testJson(@QueryParam("userid") int userid) {
    def dbServer = 'localhost'
    def dbName = 'webapi-example'
    def dbPort = '5432'
    def url = "jdbc:postgresql://${dbServer}:${dbPort}/${dbName}"
    def user = 'postgres'
    def password = 'postgres'

    def driver = 'org.postgresql.Driver'
    def sql = Sql.newInstance(url, user, password, driver)
    def rows = sql.rows("select project_name from project where user_id = ${userid}")

    rows.collect{new ResultDto(it["project_name"])}
  }
}

/** */
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class ResultDto {
  String projectName

  ResultDto() {
  }

  ResultDto(projectName) {
    this.projectName = projectName
  }
}

/*----- jersey -----*/
//JAX-RSのresource、providerに含めるクラスを指定
def ResourceClass = [
  WebApiResource.class,
]
def servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
def servletHolder = new ServletHolder(ServletContainer.class);

//サーブレットとして動作させるクラス名を指定（"HogeClass;FugaClass;…"の形）
servletHolder.setInitParameters([
  "com.sun.jersey.config.property.resourceConfigClass": "com.sun.jersey.api.core.ClassNamesResourceConfig",
  "com.sun.jersey.config.property.classnames": ResourceClass.collect{it.getName()}.join(";"),
  "com.sun.jersey.api.json.POJOMappingFeature": "true"
])
servletContext.setContextPath("/api")        //URL設定
servletContext.addServlet(servletHolder, "/*")

/*----- サーバ起動 -----*/
def server = new Server(18888);               //ポート設定
//コンテキスト定義をサーバにハンドル
def handlerList = new HandlerList()
handlerList.setHandlers(servletContext)
server.setHandler(handlerList)

println "APIサーバーを起動します"
server.start()                               //サーバ起動

/*----- 終了時 -----*/
addShutdownHook {
  println "APIサーバーを停止します"
  server.stop()
}
