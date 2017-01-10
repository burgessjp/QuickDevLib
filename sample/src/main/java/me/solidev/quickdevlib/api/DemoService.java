package me.solidev.quickdevlib.api;

import me.solidev.quickdevlib.entity.NewsResult;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by _SOLID
 * Date:2016/11/7
 * Time:13:55
 * Desc:
 */

public interface DemoService {
    String BASE_URL = "https://raw.githubusercontent.com/burgessjp/QuickDevLib/master/sample/src/main/res/raw/";

    @GET("news_list.json")
    Observable<NewsResult> getNewsData();
}
