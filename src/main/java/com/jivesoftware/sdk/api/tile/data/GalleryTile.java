/*
 *
 *  * Copyright 2013 Jive Software
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *       http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.jivesoftware.sdk.api.tile.data;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Maps;

/**
 * Created by rrutan on 2/4/14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class GalleryTile extends BaseTile {

    public static final String TYPE = "GALLERY";

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String title;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private List<GalleryImage> contents;

    private TileAction action;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private Map<String,Object> config;

    public GalleryTile() {
        title = null;
        contents = Lists.newArrayList();
        action = null;
        config = Maps.newHashMap();
    } // end constructor

    public void addGalleryImage(GalleryImage image) {
      contents.add(image);
    } // end addGalleryImage

    public void removeGalleryImage(GalleryImage image) {
      contents.remove(image);
    } // end removeGalleryImage

    public void setDisableFullScreenView(boolean disableFullScreenView) {
        config.put("disableFullScreenView",String.valueOf(disableFullScreenView));
    } // end setDisableFullScreenView

    public void setHideCaptions(boolean hideCaptions) {
        config.put("hideCaptions",String.valueOf(hideCaptions));
    } // end setHideCaptions

    public void setShowAdditionalLink(boolean showAdditionalLink) {
        config.put("showAdditionalLink",String.valueOf(showAdditionalLink));
    } // end setShowAdditionalLink

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GalleryImage> getContents() {
        return contents;
    }

    public void setContents(List<GalleryImage> contents) {
        this.contents = contents;
    }

    public TileAction getAction() {
        return action;
    }

    public void setAction(TileAction action) {
        this.action = action;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GalleryTile that = (GalleryTile) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (config != null ? !config.equals(that.config) : that.config != null) return false;
        if (contents != null ? !contents.equals(that.contents) : that.contents != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (config != null ? config.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GalleryTile{" +
                "title='" + title + '\'' +
                ", contents=" + contents +
                ", action=" + action +
                ", config=" + config +
                '}';
    }

} // end class
