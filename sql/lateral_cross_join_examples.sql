SELECT	
workflow_instance_variables -> 'claimPaidDate' ->> 'value',
(VALUE ->> 'start')
  FROM insurance2.claim 
  CROSS JOIN LATERAL jsonb_array_elements((workflow_instance_variables -> 'stateLog' ->> 'value')::jsonb) 
  WHERE 
  (VALUE ->> 'state') = 'Paid' AND 
	(
	  workflow_instance_variables -> 'claimPaidDate' ->> 'value' IS NULL 
	  OR (workflow_instance_variables -> 'claimPaidDate' ->> 'value')::timestamptz::date != 
	  (VALUE ->> 'start')::timestamptz::date
);

SELECT cl.id,
       (select(value ->> 'start')
        FROM claim
        CROSS JOIN LATERAL jsonb_array_elements((workflow_instance_variables -> 'stateLog' ->> 'value')::JSONB)
        WHERE (value ->> 'state') = 'In Replacement'
          AND id = cl.id) AS replacement_date,
       (select(value ->> 'start')
        FROM claim
        CROSS JOIN LATERAL jsonb_array_elements((workflow_instance_variables -> 'stateLog' ->> 'value')::JSONB)
        WHERE (value ->> 'state') = 'Paid'
          AND id = cl.id) AS paid_date,
       (select(value ->> 'start')
        FROM claim
        CROSS JOIN LATERAL jsonb_array_elements((workflow_instance_variables -> 'stateLog' ->> 'value')::JSONB)
        WHERE ((value ->> 'state') = 'Closed w/o Pay'
               OR (value ->> 'state') = 'Denied')
          AND id = cl.id) AS closed_date
FROM claim cl
