# NASA SQE

NASA Software Quality Engineer challenge

## Basic API specification

### Root API: [https://images-api.nasa.gov](https://images-api.nasa.gov)

### Endpoints:
- /search
- /asset/{nasa_id}
- /metadata/{nasa_id}
- /captions/{nasa_id}

### Search parameters
All parameters are optional, string type. At least one parameter must me specified.

**GET /search/q={q}**

- *q* Free text search terms to compare to all indexed metadata.
- *center* NASA center which published the media.
- *description* Terms to search for in “Description” fields.
- *description_508* Terms to search for in “508 Description” fields.
- *keywords* Terms to search for in “Keywords” fields. Separate multiple values with commas.
- *location* Terms to search for in “Location” fields.
- *media_type* Media types to restrict the search to. Available types: [“image”, “audio”]. Separate multiple values with commas.
- *nasa_id* The media asset’s NASA ID.
- *photographer* The primary photographer’s name.
- *secondary_creator* A secondary photographer/videographer’s name.
- *title* Terms to search for in “Title” fields.
- *year_start* The start year for results. Format: YYYY.
- *year_end* The end year for results. Format: YYYY.

### Retrieving a media asset’s manifest parameters
Parameter is string type, required.

**GET /asset/{nasa\_id}**

- *nasa\_id* Free text search terms to compare to all indexed metadata.

### Retrieving a media asset’s metadata location parameters

Parameter is string type, required.

**GET /metadata/{nasa\_id}**

- *nasa\_id* The media asset’s NASA ID.

### Retrieving a video asset’s captions location parameters

Parameter is string type, required.

**GET /captions/{nasa\_id}**

- *nasa\_id* The video asset’s NASA ID.

# QA

## Security

For the sake os automated security tests, I'd verify the server doesn't disclosures any sensitive information.

I'd use the prepared Java HttpClient class, extended with reading HTTP response headers. The checks which should be done are
HTTP headers verifications for HTTP server or application server version disclosure. At this specific instance, the API provides
information about Web server (nginx/1.4.6) and OS version (Ubuntu). This information should would fail the sensitive information disclosure test. Another informatiod I'd look for is information about application server version or infrastructure inforation. There shouldn't be for example Set-Cookie HTTP hedader. This NASA API seems to be alrighnt in this matter.

Next test would be a SQL injection test. I'd fire requests like:

*GET https://images-api.nasa.gov/search?center=test' OR 1=1;--*

I'd check the application respond with the HTTP 400 status and relevant error message.

Another test is a test of SSL connection parameters. I'm not sure how this is done in Java, but it can be verified by simple shell script using OpenSSL utility, which can list all supported ciphers and hash algorithms. In Java there is a library BouncyCastle, which could be probably used for this purpose. Then I'd compare the list of supported ciphers with database of ciphers known to have security issues.

## Performance

In the terms of performance, I'd collect the required performance metrics. Either specified by business requirements or based on previous utilisation statistics or expected load in the future.

The tool I'd use is JMeter. As the API is quite simple, I'd use the request mix based on previous experience. For example 60% of queries is generic search, 20% is search by photographer, 10% is request for metadata and 10% are asset search queries. Then I'd run test with ie. 100 request per minute for 20 minutes. Durimg the test I'd monitor system resurces of the Web Server, Application server, databases, network utilisation etc. The test duration and number of transactions per minute is just an example. Real numbers depends on proper system sizing.

Another test would be a stress test, where I'd increase number of transactions per minute until the system stops responding correctly. This could be for example HTTP 500 status codes, Java stacktrace dumps, connection timeouts, etc. After the test is stopped, the system should recover withou administrator intervention.


## Functionality

As of functionality, I've implemented data integrity check for search result.
Unfortunately, 

    
