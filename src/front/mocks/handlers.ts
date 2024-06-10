import {http, HttpResponse} from "msw";

export const handlers = [
    http.get('/artists/:id', ({params}) => {
               if (params.id && params.id == '132') {
                   return HttpResponse.json({
                       id: 132,
                       name: "Alcest",
                       bio: "Alcest — французская пост-блэк-метал-группа из города Баньоль-сюр-Сез. " +
                           "Её лидером и основателем является Neige (Стефан По). Группа была образована" +
                           " в 1999 году как сольный блэк-метал-проект Нежа, вскоре превратившийся в трио.",
                       imageUrl: "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.npr.org%2F2012%2F03%2F27%2F149417112%2Falcest-making-metal-beautiful&psig=AOvVaw3Gg7S1eeQCDcpXQyIq9xiE&ust=1717068714709000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCLDCic7hsoYDFQAAAAAdAAAAABAE",
                       albumsPreview: [
                           {
                               id: 555,
                               name: "Souvenirs d'un autre monde",
                               genre: "Post-Black Metal, Shoegaze",
                               releaseDate: 2007,
                               tracksCount: 6,
                               coverUrl: "https://upload.wikimedia.org/wikipedia/en/3/34/Alcest_Souvenirs_dun_autre_monde.jpg"
                           },
                           {
                               id: 556,
                               name: "Écailles de Lune",
                               genre: "Post-Black Metal, Shoegaze",
                               releaseDate: 2010,
                               tracksCount: 6,
                               coverUrl: "https://upload.wikimedia.org/wikipedia/en/f/f7/Alcest-ecailes.jpg"
                           },
                           {
                               id: 557,
                               name: "Les Voyages de l'Âme",
                               genre: "Post-Black Metal, Shoegaze",
                               releaseDate: 2012,
                               tracksCount: 8,
                               coverUrl: "https://upload.wikimedia.org/wikipedia/en/0/0b/Alcest_Les_Voyages_de_lAme.jpg"
                           },
                           {
                               id: 558,
                               name: "Shelter",
                               genre: "Post-Metal, Shoegaze",
                               releaseDate: 2014,
                               tracksCount: 8,
                               coverUrl: "https://upload.wikimedia.org/wikipedia/en/3/38/Shelter_cover.jpg"
                           },
                           {
                               id: 559,
                               name: "Kodama",
                               genre: "Post-Black Metal, Shoegaze",
                               releaseDate: 2016,
                               tracksCount: 6,
                               coverUrl: "https://upload.wikimedia.org/wikipedia/en/3/36/Alcest_Kodama.jpg"
                           },
                           {
                               id: 560,
                               name: "Spiritual Instinct",
                               genre: "Post-Black Metal, Shoegaze",
                               releaseDate: 2019,
                               tracksCount: 6,
                               coverUrl: "https://upload.wikimedia.org/wikipedia/en/e/ea/Alcest_Spiritual_Instinct.jpg"
                           }
                       ]
                   })
               }
               else return new HttpResponse(null, {status: 404})
    })
]