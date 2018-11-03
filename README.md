# NASA SQE

NASA Software Quality Engineer challenge

## Basic API specification

### Root API: [https://images-api.nasa.gov](https://images-api.nasa.gov)

### Endpoints:
- /search
- /asset/{nasa_id}
- /metadata/{nasa_id}
- /captions/{nasa_id}

### Parameters
All parameters are optional, string type. At least one parameter must me specified.

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


## Security

QA related to security

## Performance

QA related to API performance

## Functionality

QA related to functionality
