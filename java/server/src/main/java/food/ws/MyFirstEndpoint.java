package food.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
public class MyFirstEndpoint {
    //    @Get
//    @Path("1")
//    @Produces(MediaType.APPLICATION_JSON)
//    public BasicStringJson basicStringJson() {
//        return BasicStringJson;
//    }
    @GET
    public String basicStringJson() {
        return "HELLO WORLD";
    }

    @GET
    @Path("hi")
    public String basicStringJson2() {
        return "hi";
    }

}