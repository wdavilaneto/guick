Feature: cheapest hotel

    Uma rede de hotéis em Miami gostaria de criar uma plataforma digital de reservas.
    A rede é composta por três hotéis: Lakewood, Bridgewood e Ridgewood.
    Cada hotel tem taxas diferenciadas para dia de semana ou final de semana,
    incluindo taxas específicas para participantes do programa de fidelidade.
    Adicionalmente, cada hotel tem uma classificação, indicando a excelência do serviço.

    Ex:
    Hotel Classifi. ["name", "stars", prices=["weekday" , "weekend" , "weekdayr" , "weekendr"] ]
    [{ "name":"Lakewood" , "stars":3, "prices":[110 , 80 , 90 , 80]},
     { "name":"Bridgewood", "stars":4, "prices":[160, 110, 60 , 50]},
     { "name":"Ridgewood", "stars":5, "prices":[220, 110, 150, 40]}
    ]

    Escreva um programa para encontrar o hotel mais barato.
    A entrada do programa será uma sequência de datas para um cliente participante ou não do programa de fidelidade.
    Utilize "Regular" para denominar um cliente normal e "Reward" para um cliente participante do programa de fidelidade
    A saída deverá ser o hotel disponível mais barato e em caso de empate, o hotel com a maior classificação
        deverá ser retornado.

    Entrada
        {tipo_do_cliente: [<data1>, <data2>, <data3>] }

    Scenario Outline: Find cheapest Hotel
        Given a <tipo_do_cliente> client
        When I search for <data1> and <data2> and <data3>
        Then The cheapest hotel should be <result>

        Examples:
            | tipo_do_cliente |    data1     |   data2       |    data3     | result     |
            | REGULAR         | "2009-03-16" |  "2009-03-17" | "2009-03-18" | Lakewood   |
            | REGULAR         | "2009-03-20" |  "2009-03-21" | "2009-03-22" | Bridgewood |
            | REWARD          | "2009-03-26" |  "2009-03-27" | "2009-03-28" | Ridgewood  |

