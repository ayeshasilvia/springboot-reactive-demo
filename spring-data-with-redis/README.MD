Run using:
`gradle composeUp bootRun`

To view the games list:
`http :8080/games`

To view a particular game:
`http :8080/games/{id}`

To view all the events relevant to a particular game:
`http -S :8080/games/{id}/events`

Following endpoints use the Reactive route config (th3y ultimately get the same results as above)

To view the games list:
`http :8080/reactive/games`

To view a particular game:
`http :8080/reactive/games/{id}`

To view all the events relevant to a particular game:
`http -S :8080/reactive/games/{id}/events`

`gradle composeDown`
`gradle --stop`