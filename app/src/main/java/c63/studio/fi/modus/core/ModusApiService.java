package c63.studio.fi.modus.core;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.UUID;

import c63.studio.fi.modus.core.models.Project;
import c63.studio.fi.modus.core.models.Team;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ModusApiService {

    @NonNull
    @GET
    Observable<List<Project>> fetchProjectListForAuthenticatedUser();

    @NonNull
    @GET
    Observable<List<Project>> fetchProjectListByTeam(@Query("team-id") UUID teamId);

    @NonNull
    @GET
    Observable<List<Team>> fetchTeamListForAuthenticatedUser();
}
