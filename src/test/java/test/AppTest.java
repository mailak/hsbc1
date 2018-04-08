package test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Scanner;

import org.testng.annotations.Test;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.Repos;
import com.jcabi.github.RtGithub;
import com.jcabi.github.Search;

import org.hamcrest.Matchers;
import org.hamcrest.MatcherAssert;

public class AppTest
{	
	private static final String TOKEN = getToken();
	private static final Coordinates COORDINATES = new Coordinates.Simple("mailak", "new_repo");
    
	@Test(description = "User should login and create a repository", priority = 1)
	public void createRepository() throws Exception{
		Github github = new RtGithub(TOKEN);
		github.repos().create(new Repos.RepoCreate("new_repo", false));
	}

	@Test(description = "User should login and push commits", priority = 2)
	public void pushCommit() throws Exception {
		//to be implemented
	}
	
	@Test(description = "User should login and create pull request", priority = 3)
	public void createPullRequest() throws IOException{
		Repo repo = getRepo();
		repo.issues().create("test issue", "TheTest");
		Github github = new RtGithub(TOKEN);
		MatcherAssert.assertThat( github.search().issues("TheTest", "updated", Search.Order.DESC, new EnumMap<Search.Qualifier, String>(Search.Qualifier.class) ), Matchers.not(Matchers.emptyIterable()) );
	}
	
	@Test(description = "User should login and accept pull request", priority = 4)
	public void acceptPullRequest(){
		//to be implemented
	}
	
	@Test(description = "User should login and delete a repository", priority = 5)
	public void deleteRepository() throws IOException{
		Github github = new RtGithub(TOKEN);
		Repos repos = github.repos();
		repos.remove(COORDINATES);
	}
	
	
	private static String getToken() {
		String token = "";
		try{
			File x = new File("C:\\token.txt");
			Scanner sc = new Scanner(x);
			token = sc.next();
			sc.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found! " + e);
		}
		return token;
	}

	public Repo getRepo() {
		Github github = new RtGithub(TOKEN);
		Repo repo = github.repos().get(COORDINATES);
		return repo;
	}

}
