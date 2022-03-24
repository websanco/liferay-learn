import {FieldBase} from 'dynamic-data-mapping-form-field-type/FieldBase/ReactFieldBase.es';
import {useSyncValue} from 'dynamic-data-mapping-form-field-type/hooks/useSyncValue.es';
import React from 'react';

const Slider = ({max, min, name, onChange, predefinedValue, readOnly, value}) => (
	<input
		className="ddm-field-slider form-control slider"
		disabled={readOnly}
		id="myRange"
		max={max}
		min={min}
		name={name}
		onInput={onChange}
		type="range"
		value={value ? value : predefinedValue}
	/>
);

const Main = ({
	label,
	max,
	min,
	name,
	onChange,
	predefinedValue,
	readOnly,
	value,
	...otherProps
}) => {
	const [currentValue, setCurrentValue] = useSyncValue(
		value ? value : predefinedValue
	);

	return (
		<FieldBase
			label={label}
			name={name}
			predefinedValue={predefinedValue}
			{...otherProps}
		>
			<Slider
				max={max}
				min={min}
				name={name}
				onChange={(event) => {
					setCurrentValue(event.target.value);
					onChange(event);
				}}
				predefinedValue={predefinedValue}
				readOnly={readOnly}
				value={currentValue}
			/>
		</FieldBase>
	);
};

Main.displayName = 'Slider';

export default Main;
