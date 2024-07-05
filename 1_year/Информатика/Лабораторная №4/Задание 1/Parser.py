import queue

class Tag:
    def __init__(self, i, name):
        self.end = i
        self.name = name
def Find_key(d, key):
    for i in d.keys():
        if i == key:
            return 1
    return 0
def Text_correcter(text):
    for i in range(len(text)):
        if text[i] not in [" ", "\t", "\n"]:
            text = text[i : ]
            break
    if len(text) != 0 and text[0] =="\"" and text[len(text) - 1] == "\"":
        text = text[1 : len(text) - 1]
    return text
def deserialize(text):
    text = Text_correcter(text)
    q = queue.LifoQueue()
    if len(text) == 0 or text[0] != "<":
        return text
    block = []
    for i in range(len(text)):
        if text[i] == "<":
            j = i + 1
            while text[j] != ">":
                j += 1
            tag = text[i : j + 1]
            if tag[ : 2] == "<?":
                i = j - 1
                continue
            if tag[ : 2] == "</":
                t = q.get()
                if q.empty():
                    if t.name == "<root>":
                        return deserialize(text[t.end + 1 : i])
                    #block[t.name[1 : len(t.name) - 1]] = deserialize(text[t.end + 1 : i])
                    block.append([t.name[1 : len(t.name) - 1], deserialize(text[t.end + 1 : i])])
            else:
                q.put(Tag(j, tag))
    answers = {}
    keys = {}
    for b in block:
        if Find_key(keys, b[0]):
            keys[b[0]] += 1
        else:
            keys[b[0]] = 1
    for b in block:
        if keys[b[0]] == 1:
            answers[b[0]] = b[1]
        else:
            a = []
            for b1 in block:
                if b1[0] == b[0]:
                    a.append(b1[1])
            answers[b[0]] = a
    return answers
def serialize(p, tab = ""):
    text = ""
    if isinstance(p, str):
            return "\"" + p + "\""
    if isinstance(p, dict):
        text = "{\n"
        for i in p.keys():
            text += tab + "\t" + "\"" + i + "\"" + ": " + serialize(p[i], tab + "\t") + ",\n"
        text = text[ : len(text) - 2] + "\n"
        text += tab + "}"
    if isinstance(p, list):
        text = "[\n" + tab + "\t"
        for i in p:
            if len(p) == 1 and i == "":
                text += "\n"
                break
            text += serialize(i, tab + "\t")
            text += ",\n" + tab + "\t"
        if text[len(text) - 1] == "\t":
            text = text[ : len(text) - len(tab) - 3] + "\n"
        text += tab + "]"
    return text
file = open('Schedule.xml', 'r', encoding='utf-8')
s = file.read()
p = deserialize(s)
print(serialize(p))
