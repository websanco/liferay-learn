import React, {useState} from 'react';

import {patchObject} from '../utils/Requests';

function PatchForm() {
	const [description, setDescription] = useState('');
	const [id, setId] = useState('');
	const [name, setName] = useState('');

	const handleSubmit = () => {
		const object = {
			description: description,
			id: id,
			name: name,
		};

		patchObject(object);
	};

	return (
		<div className="App">
			<h2>Patch a J4X7 Object</h2>

			<form onSubmit={handleSubmit}>
				<input
					onChange={object => setId(object.target.value)}
					placeholder="id"
					type="text"
					value={id}
				/>

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

				<button type="submit">Patch</button>
			</form>
		</div>
	);
}

export default PatchForm;
