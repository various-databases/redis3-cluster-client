package info.hb.redis3.gaia.resources;

import info.hb.redis3.gaia.core.DatastoreInterface;
import info.hb.redis3.gaia.dao.Event;
import info.hb.redis3.gaia.dao.PushEvent;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

@Path("/events")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

	private final DatastoreInterface adapter;

	public EventResource(DatastoreInterface adapter) {
		this.adapter = adapter;
	}

	@Timed
	@POST
	public Response push(@Valid PushEvent pushEvent) {
		adapter.push(pushEvent.getName(), pushEvent.getIncrement(), pushEvent.getTimestamp());
		return Response.status(Response.Status.CREATED).build();
	}

	@Timed
	@GET
	@Path("{event}")
	public List<Event> query(@PathParam("event") String event, @QueryParam("start") Long start,
			@QueryParam("end") Long end, @QueryParam("resolution") String resolution) {
		return adapter.query(event, start, end, resolution);
	}

}
