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
 * Created by rrutan on 2/9/14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class CarouselTile extends BaseTile  {

    public enum Speed { fast, medium, slow, custom }
    public enum Transition { none, fade, slide }

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String title;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private List<CarouselItem> contents;

    private Map<String,Object> config;

    private TileAction action;

    public CarouselTile() {
        contents = Lists.newArrayList();
        config = Maps.newHashMap();
        /*** REQUIRED CONFIG PROPERTIES ***/
        setAutoPlay(true);
        setPreviewPane(true);
    } // end constructor

    public void addItem(CarouselItem item) { contents.add(item); }
    public void removeItem(CarouselItem item) { contents.remove(item); }

    public void setAutoPlay(boolean autoPlay) { config.put("autoplay",autoPlay); }
    public void setPreviewPane(boolean previewPane) { config.put("previewPane",previewPane); }
    public void setTransition(Transition transition) { config.put("transition",transition.name()); }
    public void setSpeed(Speed speed) { config.put("speed",speed.name()); }
    public void setDelay(int seconds) { config.put("delay",seconds); }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarouselTile that = (CarouselTile) o;

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
        result = 31 * result + (config != null ? config.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarouselTile{" +
                "title='" + title + '\'' +
                ", contents=" + contents +
                ", config=" + config +
                ", action=" + action +
                '}';
    }
} // end class
