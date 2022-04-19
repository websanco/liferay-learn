import React, {useState} from 'react';

import {addObject} from '../utils/Requests';

function AddForm() {
	const [description, setDescription] = useState('');
	const [name, setName] = useState('');

	const handleSubmit = () => {
		const object = {
			description: description,
			name: name,
		};

		addObject(object);
	};

	return (
		<div className="App">
			<h2>Add a J4X7 Object</h2>

			<form onSubmit={handleSubmit}>
				<input
					onChange={object => setName(object.target.value)}
					placeholder="name"
					type="text"
					value={name}
				/>
				<input
					onChange={object => setDescription(object.target.value)}
					placeholder="description"
					type="text"
					value={description}
				/>

				<button type="submit">Add</button>
			</form>
		</div>
	);
}

export default AddForm;
