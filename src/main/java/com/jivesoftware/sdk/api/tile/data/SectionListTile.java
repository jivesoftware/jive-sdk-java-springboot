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

import org.codehaus.jackson.map.annotate.JsonSerialize;

import jersey.repackaged.com.google.common.collect.Lists;

/**
 * Created by rrutan on 2/9/14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class SectionListTile extends BaseTile  {

    public static final String TYPE = "SECTIONLIST";

    public enum Styles { accordion, stacked, columns }

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String title;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private List<SectionListSection> content;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String style;

    public SectionListTile() {
        title = null;
        content = Lists.newArrayList();
        style = Styles.accordion.name();
    } // end constructor

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SectionListSection> getContent() {
        return content;
    }

    public void setContent(List<SectionListSection> content) {
        this.content = content;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setStyle(Styles style) { this.style = style.name(); }

    public void addSection(SectionListSection section) {
        content.add(section);
    } // end addSection

    public void removeSection(SectionListSection section) {
        content.remove(section);
    } // end removeSection

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionListTile that = (SectionListTile) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (style != null ? !style.equals(that.style) : that.style != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (style != null ? style.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SectionListTile{" +
                "title='" + title + '\'' +
                ", content=" + content +
                ", style='" + style + '\'' +
                '}';
    }
} // end class
