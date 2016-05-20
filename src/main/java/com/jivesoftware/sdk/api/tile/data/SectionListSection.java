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
 * Created by rrutan on 8/15/14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class SectionListSection {

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String text;

    private String desc;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private List<SectionListItem> items;

    public SectionListSection() {
        items = Lists.newArrayList();
    } // end constructor

    public void addItem(SectionListItem item) { items.add(item); }
    public void removeItem(SectionListItem item) { items.remove(item); }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<SectionListItem> getItems() {
        return items;
    }

    public void setItems(List<SectionListItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionListSection that = (SectionListSection) o;

        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SectionListSection{" +
                "text='" + text + '\'' +
                ", desc='" + desc + '\'' +
                ", items=" + items +
                '}';
    }
}

