import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DigitalResume } from './digital-resume';

describe('DigitalResume', () => {
  let component: DigitalResume;
  let fixture: ComponentFixture<DigitalResume>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DigitalResume]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DigitalResume);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
