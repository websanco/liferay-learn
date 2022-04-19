import React, {useEffect, useState} from 'react';

import {getObjects} from '../utils/Requests';

function GetForm() {
	const [objects, setObjects] = useState([]);

	const get = () => {
		getObjects()
			.then(response => response.json())
			.then(data => setObjects(data.items));
	};

	useEffect(() => {
		get();
	}, []);

	return (
		<div>
			<h2>J4X7 Objects</h2>

			{objects.map(items => (
				<div key={items.id}>
					{items.id} {items.name} {items.description}
				</div>
			))}
		</div>
	);
}

export default GetForm;
