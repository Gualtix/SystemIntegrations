import datetime
import zeep

status_array = ['STARTED', 'IN_PROGRESS', 'SHIPPED', 'COMPLETED']

print('Enter the order ID: ', end='')
id = int(input())

print('Enter order date (year): ', end='')
year = int(input())
year = 2020 if year < 2000 or year > 2100 else year

print('Enter order date (month): ', end='')
month = int(input())
month = 1 if month < 1 or month > 12 else month

print('Enter order date (day): ', end='')
day = int(input())
day = 1 if day < 1 or day > 31 else day

date = datetime.datetime(year, month, day)

print('Enter customer name: ', end='')
name = input()

print("""Enter status
[0] STARTED
[1] IN_PROGRESS
[2] SHIPPED
[3] COMPLETED :
""")
status_index = int(input())
status_index = 0 if status_index < 0 or status_index > 3 else status_index
status = status_array[status_index]

client = zeep.Client('http://localhost:8080/orders/ordersservice?wsdl')
result = client.service.getOrder(id, status, date, name)
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