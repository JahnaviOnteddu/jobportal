import { TestBed } from '@angular/core/testing';

import { RecruiterApplication } from './recruiter-application';

describe('RecruiterApplication', () => {
  let service: RecruiterApplication;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecruiterApplication);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
