package resources;

import com.Models.AddSongsInPlaylistRequest;
import com.Models.CreatePlaylistRequest;
import com.Models.ExploreRequest;
import com.Models.RetrunModel;
import com.Service.SwiggyPlayService;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by nikhilagrawal on 25/08/16.
 */


@Path("/playlist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SwiggyPlayResource {

    private final static Logger logger = LoggerFactory.getLogger(SwiggyPlayResource.class);
    private SwiggyPlayService swiggyPlayService;


    @Inject
    public SwiggyPlayResource(SwiggyPlayService swiggyPlayService) {
        this.swiggyPlayService = swiggyPlayService;
    }


    @GET
    @Path("/signin")
    public Response signin() {
        return Response.ok("Demo check").build();
    }


    @POST
    @UnitOfWork
    @Path("/createplaylist")
    @Timed
    public void createplaylist(CreatePlaylistRequest createPlaylistRequest )
    {
        swiggyPlayService.addPlayList(createPlaylistRequest);
        Response.ok("saved").build();
    }


    @POST
    @UnitOfWork
    @Path("/addSonginPlayList")
    @Timed
    public void addSonginPlayList(AddSongsInPlaylistRequest createPlaylistRequest  )
    {
        swiggyPlayService.addSongsInPlayList(createPlaylistRequest);
        Response.ok("saved").build();
    }


    @POST
    @UnitOfWork
    @Path("/addTag/{tagName}")
    @Timed
    public void addTag(@PathParam("tagName") String tagName)
    {
        swiggyPlayService.addTag(tagName);
        Response.ok("saved").build();
    }

    @POST
    @UnitOfWork
    @Path("/assciateTagtoPlaylist/{playListName}/{tagName}")
    @Timed
    public void assciateTagtoPlaylist(@PathParam("playListName") String playListName,@PathParam("tagName") String tagName)
    {
        swiggyPlayService.assciateTagtoPlaylist(playListName,tagName);
        Response.ok("saved").build();
    }


    @POST
    @UnitOfWork
    @Path("/explore")
    @Timed
    public Response explore(ExploreRequest exploreRequest)
    {
        return Response.ok(swiggyPlayService.explore(exploreRequest)).build();
    }


}
