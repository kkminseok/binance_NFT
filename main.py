import time
import hmac
from requests import Request

ts = int(time.time() * 1000)
request = Request('GET', '<api_endpoint>')
prepared = request.prepare()
signature_payload = f'{ts}{prepared.method}{prepared.path_url}'.encode()
signature = hmac.new('x5xUb3U3-OMBkrowb-i8bBaaLEPxuEVyOczb6hLx'.encode(), signature_payload, 'sha256').hexdigest()

prepared.headers['FTX-KEY'] = 'gpX-tLJ0WUWYt7I2TYGYmip_nl2W972q8goXMs9R'
prepared.headers['FTX-SIGN'] = signature
prepared.headers['FTX-TS'] = str(ts)
