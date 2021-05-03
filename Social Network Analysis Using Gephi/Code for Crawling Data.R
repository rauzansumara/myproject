## install rtweet
install.packages("rtweet")

## load rtweet
library(rtweet)

## store api keys
api_key <- "your api_key"
api_secret_key <- "your api_secret_key"

## authenticate via web browser
token <- create_token(
  app = "your app name",
  consumer_key = api_key,
  consumer_secret = api_secret_key)

## search for 10,000 tweets sent from the USA
tweets <- search_tweets("#COVID19 OR @WHO", lang = "en", 
                    geocode = lookup_coords("usa"), n = 10000)
write_as_csv(tweets, "D:/Dataset")

## create lat/lng variables using all available tweet and profile geo-location data
rt <- lat_lng(tweets)

## plot state boundaries
par(mar = c(0, 0, 0, 0))
maps::map("state", lwd = .25)

## plot lat and lng points onto state map
with(rt, points(lng, lat, pch = 20, cex = 2.5, col = rgb(0, .3, .7, .75)))
