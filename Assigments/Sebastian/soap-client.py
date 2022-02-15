from itertools import product
from math import prod
import string
import zeep

print('Enter the order ID: ')
id = int(input())


client = zeep.Client('http://localhost:8080/orders/ordersservice?wsdl')
result = client.service.getOrder(id)
order = zeep.helpers.serialize_object(result)

print('\nOrder ID:', order['orderId'])
print('Status:', order['orderStatus'])
print('Date:', order['orderDate'])
print('Customer:', order['customerName'])
print('Products:')
print('==============================')

total = 0.0
for product in order['product']:   
    print('\tProduct ID:', product['id'])
    print('\tPrice:', product['price'])
    print('\tQuantity:', product['quantity'])
    print('==============================')
    total += product['price'] * product['quantity']

print('Total:', total)