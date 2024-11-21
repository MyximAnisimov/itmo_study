%факт об игре
game(among_us).

%факты о жанре
genre(mobile_game).
genre(social_game).

%факты о цвете персонажей
color(red).
color(blue).
color(green).
color(yellow).
color(black).
color(pink).
color(orange).

%факты о именах игроков
nickname(john).
nickname(jane).
nickname(bob).
nickname(alice).
nickname(mike).
nickname(chester).
nickname(max).

%факты о задачах экипажа
task(fix_wires).
task(download_data).
task(scan_visors).
task(throw_trash).
task(fix_engines).
task(kill).

%факты об именах игроков и их цветов
player(john, red).
player(jane, blue).
player(bob, blue).
player(alice, yellow).
player(mike, black).
player(max, orange).
player(chester, pink).

%факты о имени игрока и его роли
role(john, impostor).
role(jane, crewmate).
role(bob, crewmate).
role(alice, crewmate).
role(mike, impostor).
role(max, crewmate).
role(chester, crewmate).

%факты о задачах в зависимости от роли
role_task(crewmate, fix_wires).
role_task(crewmate, download_data).
role_task(crewmate, scan_visors).
role_task(crewmate, throw_trash).
role_task(crewmate, fix_engines).
role_task(impostor, kill).

%факты о задачах игрока
player_task(john, kill).
player_task(jane, fix_wires).
player_task(jane, download_data).
player_task(jane, scan_visors).
player_task(jane, throw_trash).
player_task(jane, fix_engines).
player_task(bob, fix_wires).
player_task(bob, download_data).
player_task(bob, scan_visors).
player_task(bob, throw_trash).
player_task(bob, fix_engines).
player_task(alice, fix_wires).
player_task(alice, download_data).
player_task(alice, scan_visors).
player_task(alice, throw_trash).
player_task(alice, fix_engines).
player_task(mike, kill).
player_task(max, fix_wires).
player_task(max, download_data).
player_task(max, scan_visors).
player_task(max, throw_trash).
player_task(max, fix_engines).
player_task(chester, fix_wires).
player_task(chester, download_data).
player_task(chester, scan_visors).
player_task(chester, throw_trash).
player_task(chester, fix_engines).

%правила для членов экипажа
is_crewmate(Player):-
    role(Player, crewmate).

%правила для предателей
is_imposter(Player):-
    player(Player, impostor).

%правила для определения игроков и их возможности выполнять задачи
can_complete_task(Player, Task):-
    player_task(Player, Task).

%правила проверки двух игроков на то одинаковый ли у них цвет
has_same_color(Player1, Player2):-
    player(Player1, Color),
    player(Player2, Color).

%правила проверки двух игроков на то разный ли у них цвет
has_different_color(Player1, Player2):-
    player(Player1, Color1),
    player(Player2, Color2),
    Color1 \= Color2.

%правила проверки двух игроков на то, входит ли их цвет в список предложенных
has_common_color(Player1, Player2):-
    player(Player1, Color),
    player(Player2, Color),
    (Color == red ; Color == blue ; Color == green).