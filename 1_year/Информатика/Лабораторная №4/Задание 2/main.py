import xmltodict
import pprint
import json
file = open('Schedule.xml', 'r', encoding='utf-8')
s = file.read()
pp = pprint.PrettyPrinter(indent=4)
a=pp.pprint(json.dumps(xmltodict.parse(s)))




