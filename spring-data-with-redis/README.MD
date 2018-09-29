This demo application is built following a demo shown in a talk:

https://beta.voxxed.com/#/presentation/281

The redis config was sorted using this resource:

https://github.com/hantsy/spring-reactive-sample/blob/master/README.md#data-initialization-1

Run using:
`gradle composeUp bootRun`

To view the games list:
`http -a admin:pass :8080/games`

To view a particular game:
`http -a admin:pass :8080/games/{id}`

To view all the events relevant to a particular game:
`http -S -a admin:pass :8080/games/{id}/events`

Following endpoints use the Reactive router function (th3y ultimately get the same results as above)

To view the games list:
`http -a admin:pass :8080/reactive/games`

To view a particular game:
`http -a admin:pass :8080/reactive/games/{id}`

To view all the events relevant to a particular game:
`http -S -a admin:pass :8080/reactive/games/{id}/events`

`gradle composeDown`
`gradle --stop`