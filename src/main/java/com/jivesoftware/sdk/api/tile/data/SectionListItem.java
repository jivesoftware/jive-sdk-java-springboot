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

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by rrutan on 8/15/14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class SectionListItem {

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String text;

    private TileAction action;

    private String jiveIconClasses;

    private String avatar;

    private String byline;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TileAction getAction() {
        return action;
    }

    public void setAction(TileAction action) {
        this.action = action;
    }

    public String getJiveIconClasses() {
        return jiveIconClasses;
    }

    public void setJiveIconClasses(String jiveIconClasses) {
        this.jiveIconClasses = jiveIconClasses;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionListItem that = (SectionListItem) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (byline != null ? !byline.equals(that.byline) : that.byline != null) return false;
        if (jiveIconClasses != null ? !jiveIconClasses.equals(that.jiveIconClasses) : that.jiveIconClasses != null)
            return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (jiveIconClasses != null ? jiveIconClasses.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (byline != null ? byline.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SectionListItem{" +
                "text='" + text + '\'' +
                ", action=" + action +
                ", jiveIconClasses='" + jiveIconClasses + '\'' +
                ", avatar='" + avatar + '\'' +
                ", byline='" + byline + '\'' +
                '}';
    }
}
