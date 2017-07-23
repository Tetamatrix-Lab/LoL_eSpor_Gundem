package com.erdem.kurumi.volley.Youtube;

import com.erdem.kurumi.volley.Video;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class PlaylistItem {
    private String id;
    public  String title;
    public  String description;
    public  String thumbnailUrl;
    public  String videoId;
    public  int position;
    public Video video;

    public PlaylistItem(JSONObject jsonItem) throws JSONException {
        setId(jsonItem.getString("id"));
        final JSONObject snippet = jsonItem.getJSONObject("snippet");
        position = snippet.getInt("position");
        title = snippet.getString("title");
        description = snippet.getString("description");
        thumbnailUrl = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
        videoId = snippet.getJSONObject("resourceId").getString("videoId");
    }

    public PlaylistItem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
