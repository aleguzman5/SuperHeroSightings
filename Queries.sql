use superherosightings;

select * from superorganizations;
select * from organization;
select * from organization where organizationId = 2;
select * from superPowers where superPowerId = 3;

select 
	s.`name` as 'Superhero',
    sp.`name` as 'Super Power',
	o.`name`as 'Organization',
    si.sightingDate,
    l.`name` as 'Place Seen'
from `super` s
	inner join superorganizations so on s.superId = so.superId
    inner join organization o on so.organizationId = o.organizationId
    inner join superpowers sp on s.superPowerId = sp.superPowerId
    inner join supersighting ss on s.superId = ss.superId
    inner join sighting si on ss.sightingId = si.sightingId
    inner join location l on si.locationId = l.locationId;
    
#The system must be able to report all of the organizations a particular superhero/villain belongs to.
select 
    o.*
from `super` s
	inner join superorganizations so on s.superId = so.superId
    inner join organization o on so.organizationId = o.organizationId
    where s.superId = '2';

#The system must be able to report all of the locations where a particular superhero has been seen.
select l.*
from location l 
	inner join sighting si on l.locationId = si.locationId
    inner join supersighting ss on si.sightingId = ss.sightingId
    inner join `super` s on ss.superId = s.superId
    where s.superId = 5;
    
#The system must be able to report all of the superheroes sighted at a particular location.
select s.*
from `super` s
	inner join supersighting ss on s.superId = ss.superId
    inner join sighting si on ss.sightingId = si.sightingId
    inner join location l on si.locationId = l.locationId
    where l.locationId = 1;
    
#The system must be able to report all of the members of a particular organization.
select s.*
from `super` s
	inner join superorganizations so on s.superId = so.superId
    inner join organization o on so.organizationId = o.organizationId
    where o.organizationId = 2;
    
#SQL_SELECT_ALL_SIGHTINGS_BY_SUPER_ID
select si.*
from sighting si
	inner join supersighting ss on si.sightingId = ss.sightingId
    inner join `super` s on ss.superId = s.superId
    where s.superId = 5;

#findSupersForSighting
select s.*
from `super` s
	inner join supersighting ss on s.superId = ss.superId
    inner join sighting si on ss.sightingId = si.sightingId
    where si.sightingId = 2;
    
#SQL_SELECT_LOCATION_BY_SIGHTING_ID
select l.*
from location l
	inner join sighting si on l.locationId = si.locationId
    where si.sightingId = 2;
    
#getAllSightingByLocationId
select si.*
from sighting si
	inner join location l on si.locationId = l.locationId
    where l.locationId = 1;

#The system must be able to report all sightings (hero and location) for a particular date.
select si.*
from sighting si
	where si.sightingDate = '2017/10/19';
    
select distinct l.*
from location l
	inner join sighting si on l.locationId = si.locationId
    where si.sightingDate = '2017/10/19';
    
select s.*
from `super` s
	inner join supersighting ss on s.superId = ss.superId
    inner join sighting si on ss.sightingId = si.sightingId
    where si.sightingDate = '2017/10/19';
    
#A user must be able to record a superhero/supervillain sighting for a particular location and date.
select s.*
from `super` s
	inner join supersighting ss on s.superId = ss.superId
    inner join sighting si on ss.sightingId = si.sightingId
    where si.sightingDate = '2017/10/09' and si.locationId = 3;
    
select sp.*
from superpowers sp
	inner join `super` s on sp.superPowerId = s.superPowerId
    where s.superId = 4;
    
select * 
from sighting s
	order by s.sightingDate desc limit 10;
    
insert into superpowers (`name`) 
values ('Gadgets'),
	   ('Telepathy'),
       ('Shapeshifting'),
       ('Fire Brathing'),
       ('Elasticity'),
       ('Infrared Vision'),
       ('Healing'),
       ('Superhuman Endurance'),
       ('Water Breathing'),
       ('Time Travel'),
       ('Omnilinguilism')
       