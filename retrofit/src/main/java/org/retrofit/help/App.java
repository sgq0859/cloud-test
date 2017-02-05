package org.retrofit.help;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {

	public static void main(String[] args) throws Exception {
		
//		Reader reader = new InputStreamReader(App.class.getResourceAsStream("/data.json"), "UTF-8");
//        Gson gson = new GsonBuilder().create();
//        Repo p = gson.fromJson(reader, Repo.class);
//        
//        System.err.println(p.getAvatarUrl());
        
		
		
		
		//01:获取Retrofit对象
		Retrofit retrofit = new Retrofit.Builder()  
				//02采用链式结构绑定Base url
                .baseUrl("https://api.github.com/") 
                .addConverterFactory(GsonConverterFactory.create())
                .build();//03执行操作
		//04获取API接口的实现类的实例对象
		GitHubService service = retrofit.create(GitHubService.class);
		
		Call<List<Repo>> call = service.listRepos("sgq0859");
		
		
		//同步操作，不能直接在UI线程执行
		try {
			List<Repo> repoList = call.execute().body();
			printRepoInfo(repoList);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//异步请求
		call.enqueue(new Callback<List<Repo>>() {

			public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
				List<Repo> repoList = response.body();
				printRepoInfo(repoList);
			}
			public void onFailure(Call<List<Repo>> call, Throwable t) {
			
			}});

	}

	private static void printRepoInfo(List<Repo> repoList) {
		for(Repo repo : repoList){
			System.out.println(repo.getUrl());
		}
		
		System.out.println(repoList.size());
	}

}
