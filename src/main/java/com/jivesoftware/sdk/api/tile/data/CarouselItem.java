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
public class CarouselItem {

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String titleText;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String titleLink;

    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
    private String image;

    //The URI of image (statics service) - NEED CLARITY AS TO WHAT THIS MEANS
    private String imageURI;

    private String description;

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarouselItem that = (CarouselItem) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (imageURI != null ? !imageURI.equals(that.imageURI) : that.imageURI != null) return false;
        if (titleLink != null ? !titleLink.equals(that.titleLink) : that.titleLink != null) return false;
        if (titleText != null ? !titleText.equals(that.titleText) : that.titleText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleText != null ? titleText.hashCode() : 0;
        result = 31 * result + (titleLink != null ? titleLink.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (imageURI != null ? imageURI.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarouselItem{" +
                "titleText='" + titleText + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", image='" + image + '\'' +
                ", imageURI='" + imageURI + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
} // end class
