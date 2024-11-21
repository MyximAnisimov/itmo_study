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
nickname(max).
nickname(chester).

%факты о задачах экипажа
task(fix_wires).
task(download_data).
task(scan_visors).
task(throw_trash).
task(destroy_asteroids).
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

%факты о заданиях игроков
doTask(jane, fix_wires).
doTask(bob, download_data).
doTask(alice, scan_visors).
doTask(max, destroy_asteroids).
doTask(chester, throw_trash).
doTask(john, kill).
doTask(mike, kill).

%правила для членов экипажа
is_crewmate(Player):-
    role(Player, crewmate).

%правила для предателей
is_imposter(Player):-
    player(Player, impostor).

%правила для определения игроков и их возможности выполнять задачи
can_complete_task(Player, Task):-
    is_crewmate(Player),
    task(Task).

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