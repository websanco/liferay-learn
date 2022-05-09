export const addObject = object => {
	return fetch(`http://localhost:8080/o/c/j4x7objects/`, {
		body: JSON.stringify({
			description: object.description,
			name: object.name,
		}),
		headers: {
			Authorization: 'Basic ' + btoa('test@liferay.com:learn'),
			'Content-Type': 'application/json',
		},
		method: 'POST',
	});
};

export const deleteObject = id => {
	return fetch(`http://localhost:8080/o/c/j4x7objects/${id}`, {
		headers: {
			Authorization: 'Basic ' + btoa('test@liferay.com:learn'),
			'Content-Type': 'application/json',
		},
		method: 'DELETE',
	});
};

export const getObjects = () => {
	return fetch(`http://localhost:8080/o/c/j4x7objects/`, {
		headers: {
			Authorization: 'Basic ' + btoa('test@liferay.com:learn'),
			'Content-Type': 'application/json',
		},
		method: 'GET',
	});
};

export const patchObject = object => {
	return fetch(`http://localhost:8080/o/c/j4x7objects/${object.id}`, {
		body: JSON.stringify({
			description: object.description,
			name: object.name,
		}),
		headers: {
			Authorization: 'Basic ' + btoa('test@liferay.com:learn'),
			'Content-Type': 'application/json',
		},
		method: 'PATCH',
	});
};
