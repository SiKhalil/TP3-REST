package tn.fss.calcul;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

@Path("/calcul")
@Produces(MediaType.APPLICATION_JSON)
public class CalculRest {

    private final Calculatrice calc = new Calculatrice();

    @GET
    @Path("/addition")
    public Response addition(@QueryParam("a") double a, @QueryParam("b") double b) {
        return Response.ok(new Operation("addition", a, b, calc.addition(a, b))).build();
    }

    @GET
    @Path("/soustraction")
    public Response soustraction(@QueryParam("a") double a, @QueryParam("b") double b) {
        return Response.ok(new Operation("soustraction", a, b, calc.soustraction(a, b))).build();
    }

    @GET
    @Path("/multiplication")
    public Response multiplication(@QueryParam("a") double a, @QueryParam("b") double b) {
        return Response.ok(new Operation("multiplication", a, b, calc.multiplication(a, b))).build();
    }

    @GET
    @Path("/division")
    public Response division(@QueryParam("a") double a, @QueryParam("b") double b) {
        try {
            return Response.ok(new Operation("division", a, b, calc.division(a, b))).build();
        } catch (ArithmeticException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("erreur", e.getMessage()))
                    .build();
        }
    }
}