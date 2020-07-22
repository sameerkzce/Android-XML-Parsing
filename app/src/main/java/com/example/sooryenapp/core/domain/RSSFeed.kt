package com.example.sooryenapp.core.domain

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter

/**
 * data classes for xml response parsing
 * */
@Xml(name = "feed")
data class Feed(
    @PropertyElement(name = "id") val id: String?,
    @PropertyElement(name = "title") val title: String?,
    @Element(name = "author") val author: Author?,
    @Element(name = "entry") val entryList: List<Entry>?
)

@Xml(name = "author")
data class Author(
    @PropertyElement(name = "name") val name: String?,
    @PropertyElement(name = "uri") val uri: String?
)

@Xml(name = "entry", writeNamespaces = arrayOf("im=itunes.apple.com/rss"))
data class Entry(
    @PropertyElement(name = "id ") val id: String?,
    @PropertyElement(
        name = "title",
        converter = HtmlEscapeStringConverter::class
    ) val title: String?,
    @PropertyElement(name = "im:image") val imageUrl: String?,
    @PropertyElement(name = "im:artist", converter = HtmlEscapeStringConverter::class ) val artist: String?,
    @PropertyElement(name = "im:price") val price: String?,
    @PropertyElement(
        name = "content",
        converter = HtmlEscapeStringConverter::class
    ) val content: String,
    @Element val link: Link,
    @Element val category: Category
)

@Xml(name = "category")
data class Category(
    @Attribute(name = "label") val label: String?
)

@Xml(name = "link", writeNamespaces = arrayOf("im=itunes.apple.com/rss"))
data class Link(
    @PropertyElement(name = "im:duration ") val duration: String?,
    @Attribute(name = "type") val type: String?,
    @Attribute(name = "href") val audioLink: String?
)