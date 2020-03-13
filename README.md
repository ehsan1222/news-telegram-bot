# News Telegram Bot

Get RSS data from news sites and send to telegram channel with bot.<br/>
Check updated news in once hour and send to channel, if any new news added to RSS site.


#### RSS Format 
```
<?xml version="1.0" encoding="UTF-8"?>
<rss xmlns:content="http://purl.org/rss/1.0/modules/content/" version="2.0">
  <channel>
    <title>{{Site name}}</title>
    <link>{{Site address}}</link>
    <description>{{Description}}</description>
    <copyright>{{Copywite Message}}</copyright>
    <lastBuildDate>{{Last Message Date in this RSS file}}</lastBuildDate>
    <generator>{{}}</generator>
    <item>
        <title>{{News Title}}</title>
        <link>{{News link address}}</link>
        <description>{{News Description}}</description>
        <content:encoded />
        <enclosure url="{{News Image Address}}" type="{{Image Type}}" />
        <category domain="{{News Domain}}">{{News Domain description}}</category>
        <pubDate>{{Publish Date}}</pubDate>
        <guid>{}</guid>
    </item>
    <item>
        ....
        ....
        ....
    </item>
    .
    .
    .
  </channel>
</rss>
```